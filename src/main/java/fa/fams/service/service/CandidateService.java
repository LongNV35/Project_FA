package fa.fams.service.service;

import java.util.List;
import java.util.Set;
import fa.fams.model.Candidate;

public interface CandidateService {

  Candidate getCandidateById(int candidateId);
  
  Candidate save(Candidate candidate);

  String tranferCandidate(int candidateId, Set<String> roles, String level);

  Candidate findByCandidateId(int id);

  List<Candidate> getAllCandidate();
  
  boolean saveCandidate(Candidate candidate);
  
  boolean updateStatusInActive(int id);
}