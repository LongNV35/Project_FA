package fa.fams.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(schema = "FAMS_G2")
public class University {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "university_id")
  private int universityId;

  @Column(name = "university_name", nullable = false)
  @NonNull
  private String universityName;

  @Column(name = "remarks")
  private String remarks;

  @OneToMany(mappedBy = "university")
  private List<CandidateProfile> candidateProfile;

}
