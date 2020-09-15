package com.qiyue.base.id;


import java.util.Calendar;

public class UniqueId {
    // 系统开始时间截 (2020-01-01 00:00:00)
    private static final long START_TIME = 1577808000177L;
    // 机器id所占的位数
    private static final long WORKER_ID_BITS = 5L;
    // 数据标识id所占的位数
    private static final long DATA_CENTER_ID_BITS = 5L;
    // 支持的最大机器id(十进制)，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
    // -1L 左移 5位 (worker id 所占位数) 即 5位二进制所能获得的最大十进制数 - 31
    private static final long MAX_WORKER_ID = ~ (-1L << WORKER_ID_BITS);
    // 支持的最大数据标识id - 31
    private static final long MAX_DATA_CENTER_ID = ~ (-1L << DATA_CENTER_ID_BITS);
    // 序列在id中占的位数
    private static final long SEQUENCE_BITS = 12L;
    // 机器ID 左移位数 - 12 (即末 sequence 所占用的位数)
    private static final long WORKER_ID_MOVE_BITS = SEQUENCE_BITS;
    // 数据标识id 左移位数 - 17(12+5)
    private static final long DATA_CENTER_ID_MOVE_BITS = SEQUENCE_BITS + WORKER_ID_BITS;
    // 时间截向 左移位数 - 22(5+5+12)
    private static final long TIMESTAMP_MOVE_BITS = SEQUENCE_BITS + WORKER_ID_BITS + DATA_CENTER_ID_BITS;
    // 生成序列的掩码(12位所对应的最大整数值)，这里为4095 (0b111111111111=0xfff=4095)
    private static final long SEQUENCE_MASK = ~ (-1L << SEQUENCE_BITS);
    //=================================================Works's Parameter================================================
    /**
     * 工作机器ID(0~31)
     */
    private long workerId;
    /**
     * 数据中心ID(0~31)
     */
    private long dataCenterId;
    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;
    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;
    //===============================================Constructors=======================================================

    /**
     * 构造函数
     *
     * @param workerId     工作ID (0~31)
     * @param dataCenterId 数据中心ID (0~31)
     */
    public UniqueId(long workerId, long dataCenterId) {
        if (workerId > MAX_WORKER_ID || workerId < 0) {
            throw new IllegalArgumentException(String.format("Worker Id can't be greater than %d or less than 0", MAX_WORKER_ID));
        }
        if (dataCenterId > MAX_DATA_CENTER_ID || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("DataCenter Id can't be greater than %d or less than 0", MAX_DATA_CENTER_ID));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
    }

    // ==================================================Methods========================================================
    // 线程安全的获得下一个 ID 的方法
    public synchronized long nextId() {
        long timestamp = currentTime();
        //如果当前时间小于上一次ID生成的时间戳: 说明系统时钟回退过 - 这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & SEQUENCE_MASK;
            //毫秒内序列溢出 即 序列 > 4095
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = blockTillNextMillis(lastTimestamp);
            }
        }
        //时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }
        //上次生成ID的时间截
        lastTimestamp = timestamp;
        //移位并通过或运算拼到一起组成64位的ID
        return ((timestamp - START_TIME) << TIMESTAMP_MOVE_BITS) //
                | (dataCenterId << DATA_CENTER_ID_MOVE_BITS) //
                | (workerId << WORKER_ID_MOVE_BITS) //
                | sequence;
    }

    // 阻塞到下一个毫秒 即 直到获得新的时间戳
    protected long blockTillNextMillis(long lastTimestamp) {
        long timestamp = currentTime();
        while (timestamp <= lastTimestamp) {
            timestamp = currentTime();
        }
        return timestamp;
    }

    // 获得以毫秒为单位的当前时间
    protected long currentTime() {
        return System.currentTimeMillis();
    }

    //====================================================Test Case=====================================================
    public static void main(String[] args) {
//        test();
        snow();
    }

    public static void snow() {
        UniqueId idWorker = new UniqueId(2, 0);
        for (int i = 0; i < 10; i++) {
            long id = idWorker.nextId();
            //System.bo.println(Long.toBinaryString(id));
            System.out.println(id);
        }
    }

    public static void test() {
        System.out.println((1L << 40) / (365 * 24 * 60 * 60 * 1000L));
        System.out.println(31 * 31 * 4095);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, 0, 1, 0, 0, 0);
        System.out.println(calendar.getTimeInMillis());
        UniqueId idWorker = new UniqueId(0, 0);
        long timestamp = System.currentTimeMillis();
        long t = (timestamp - UniqueId.START_TIME) << UniqueId.TIMESTAMP_MOVE_BITS;
    }
}
