package in.hefshine.quizsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.hefshine.quizsystem.entity.Answer;


@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long>{

	

}
