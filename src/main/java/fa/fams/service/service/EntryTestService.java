package fa.fams.service.service;

import java.util.List;

import fa.fams.common.valueobjects.MessageVo;
import fa.fams.model.Candidate;
import fa.fams.model.EntryTest;

public interface EntryTestService {
  List<EntryTest> getAllByCandidateId(int candidateId);

  List<EntryTest> saveOrUpdateWithCandidate(List<String> entryTest,
      Candidate candidate, int employeeId, MessageVo messageVo);

}