package fa.fams.service.service;

import java.util.List;
import fa.fams.common.valueobjects.MessageVo;
import fa.fams.model.Candidate;
import fa.fams.model.InterView;

public interface InterViewService {
  List<InterView> getAllByCandidateId(int candidateId);

  List<InterView> saveOrUpdateWithCandidate(List<String> interviews,
      Candidate candidate, int employeeId, MessageVo messageVo);
}