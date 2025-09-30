package com.neosoft.neosoftToDo.Repository;
import com.neosoft.neosoftToDo.Entity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeoRepository extends JpaRepository<TodoEntity, Long> {

}
