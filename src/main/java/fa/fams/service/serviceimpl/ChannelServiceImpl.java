package fa.fams.service.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fa.fams.model.Channel;
import fa.fams.repository.ChannelRepository;
import fa.fams.service.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {

  @Autowired
  private ChannelRepository channelRepository;

  @Override
  public List<Channel> findAll() {
    return channelRepository.findAll();
  }

  @Override
  public Channel findByChannelId(int id) {
    return channelRepository.findByChannelId(id);
  }

}
