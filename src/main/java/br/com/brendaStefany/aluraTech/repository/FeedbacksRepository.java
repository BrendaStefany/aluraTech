package br.com.brendaStefany.aluraTech.repository;

import br.com.brendaStefany.aluraTech.domain.Feedbacks;
import br.com.brendaStefany.aluraTech.domain.FeedbacksId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbacksRepository extends JpaRepository<Feedbacks, FeedbacksId> {

    @Query(value =
            "SELECT \n" +
            "    c.code,\n" +
            "    SUM(CASE WHEN f.note <= 6 THEN 1 ELSE 0 END) AS count_notes_6,\n" +
            "    SUM(CASE WHEN f.note BETWEEN 7 AND 8 THEN 1 ELSE 0 END) AS count_notes_7_8,\n" +
            "    SUM(CASE WHEN f.note BETWEEN 9 AND 10 THEN 1 ELSE 0 END) AS count_notes_9_10,\n" +
            "    COUNT(f.code) AS total_feedbacks\n" +
            "FROM \n" +
            "    courses c\n" +
            "LEFT JOIN \n" +
            "    registrations r ON c.code = r.code\n" +
            "LEFT JOIN \n" +
            "    feedbacks f ON c.code = f.code AND r.username = f.username\n" +
            "GROUP BY \n" +
            "    c.code\n" +
            "HAVING \n" +
            "    COUNT(r.code) > 4",
            nativeQuery = true)
    List<Object[]> findFeedbacksForNPS();


}
