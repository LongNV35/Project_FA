package fa.fams.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "FAMS_G2")
public class Offer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "offer_id")
  private int offerId;

  @Column(name = "job_rank")
  private int jodRank;

  @Column(name = "technology")
  private String technology;

  @Column(name = "contract_type")
  private String contractType;

  @Column(name = "offer_salary")
  private int offerSalary;

  @Column(name = "remarks", nullable = true)
  private String remarks;

  @OneToOne(mappedBy = "offer")
  private InterView interView;

}
