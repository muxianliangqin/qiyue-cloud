package com.qiyue.crawler.dao.em;

import com.qiyue.crawler.dao.entity.NewsEntity;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class CrawlerEntityManager {
    @Autowired
    private EntityManager entityManager;

    @Transactional
    public void newsBatchUpdate(List<NewsEntity> list) {
        try {
            String sql = "insert ignore into news (url,title,category_id) values (?,?,?)";
            SessionImplementor session = entityManager.unwrap(SessionImplementor.class);
            Connection conn = session.connection();
            PreparedStatement ps = conn.prepareStatement(sql);
            conn.setAutoCommit(false);
            for (int i = 0; i < list.size(); i++) {
                ps.setString(1, list.get(i).getUrl());
                ps.setString(2, list.get(i).getTitle());
                ps.setInt(3, list.get(i).getCategoryId());
                ps.addBatch();
                if ((i + 1) % 50 == 0) {
                    int[] ints = ps.executeBatch();
                }
            }
            int[] ints = ps.executeBatch();
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Transactional
    public void keywordWebSet(int webId, List<Integer> categories, List<Integer> keywords) {
        try {
            String sql = "insert ignore into web_keyword (web_id,category_id,keyword_id) " +
                    "values (?,?,?)";
            SessionImplementor session = entityManager.unwrap(SessionImplementor.class);
            Connection conn = session.connection();
            PreparedStatement ps = conn.prepareStatement(sql);
//            conn.setAutoCommit(false);
            for (int i = 0; i < categories.size(); i++) {
                for (int j = 0; j < keywords.size(); j++) {
                    ps.setInt(1, webId);
                    ps.setInt(2, categories.get(i));
                    ps.setInt(3, keywords.get(j));
                    ps.addBatch();
                    if ((i*j + 1) % 500 == 0) {
                        int[] ints = ps.executeBatch();
                    }
                }
            }
            int[] ints = ps.executeBatch();
//            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
