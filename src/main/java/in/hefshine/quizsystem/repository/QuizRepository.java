package in.hefshine.quizsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.hefshine.quizsystem.entity.Quiz;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {
//	Quiz findById(Long id);
	 @Query("SELECT q FROM Quiz q LEFT JOIN FETCH q.questions WHERE q.id = :id")
	    Quiz findByIdWithQuestions(@Param("id") Long id);
	 
	 @Query("SELECT q FROM Quiz q WHERE q.title = :title")
	 Quiz findByTitle(@Param("title") String title);

}