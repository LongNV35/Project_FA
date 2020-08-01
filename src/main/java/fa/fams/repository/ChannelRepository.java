package fa.fams.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import fa.fams.model.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Integer> {
  Channel findByChannelId(int id);
}
