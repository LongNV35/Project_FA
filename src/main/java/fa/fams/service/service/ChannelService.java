package fa.fams.service.service;

import java.util.List;
import fa.fams.model.Channel;

public interface ChannelService {

  List<Channel> findAll();

  Channel findByChannelId(int id);

}