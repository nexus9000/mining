package edu.itstep.blockchain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.itstep.blockchain.domain.BlockPersistent;
@Repository
public interface BlockPersistentRepo extends JpaRepository<BlockPersistent, Long>{

}
