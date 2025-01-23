package in.hefshine.quizsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.hefshine.quizsystem.entity.Question;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

	@Query("SELECT q FROM Question q WHERE q.quiz.id = :quizId")
	List<Question> findByQuizId(@Param("quizId") Long quizId);
	
	
	@Query("SELECT q FROM Question q WHERE q.quiz.title = :title")
	List<Question> findByQuizTitle(@Param("title") String title);


}
