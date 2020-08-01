package fa.fams.service.serviceimpl.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import fa.fams.common.log.Log4J;
import fa.fams.common.valueobjects.DashboardVo;
import fa.fams.repository.TraineeRepository;
import fa.fams.service.serviceimpl.TraineeServiceImpl;

@SpringBootTest
class TraineeServiceImplTest {
	@Mock
	private TraineeRepository traineeRepository;

	@InjectMocks
	TraineeServiceImpl traineeService;

	private String status;
	private String location;

	/**
	 * test when database is empty.
	 */
	@Test
	void testCountTraineeByStatusLocationUC1() {
		status = "Enrolled";
		location = "Location1";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(traineeRepository.countTraineeByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Empty", 0));
		List<DashboardVo> listActual = traineeService.countTraineeByStatusLocation(status, location);
		Log4J.getLogger().info("testCountTraineeByStatusLocationUC1 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status="Enrolled", location="Location1".
	 */
	@Test
	void testCountTraineeByStatusLocationUC2() {
		String status = "Enrolled";
		String location = "Location1";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		Object[] queryReturn = { "Java", 3 };
		resultRepository.add(queryReturn);
		when(traineeRepository.countTraineeByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Java", 3));
		List<DashboardVo> listActual = traineeService.countTraineeByStatusLocation(status, location);
		Log4J.getLogger().info("testCountTraineeByStatusLocationUC2 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status="Enrolled", location="All".
	 */
	@Test
	void testCountTraineeByStatusLocationUC3() {
		status = "Enrolled";
		location = "All";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		Object[] queryReturn = { "Java", 4 };
		resultRepository.add(queryReturn);
		when(traineeRepository.countTraineeByStatusLocation(status, "%%")).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Java", 4));
		List<DashboardVo> listActual = traineeService.countTraineeByStatusLocation(status, location);
		Log4J.getLogger().info("testCountTraineeByStatusLocationUC3 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status="Enrolled", location=null.
	 */
	@Test
	void testCountTraineeByStatusLocationUC4() {
		status = "Enrolled";
		location = null;
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(traineeRepository.countTraineeByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Empty", 0));
		List<DashboardVo> listActual = traineeService.countTraineeByStatusLocation(status, location);
		Log4J.getLogger().info("testCountTraineeByStatusLocationUC4 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status="Enrolled", location="".
	 */
	@Test
	void testCountTraineeByStatusLocationUC5() {
		status = "Enrolled";
		location = "";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(traineeRepository.countTraineeByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Empty", 0));
		List<DashboardVo> listActual = traineeService.countTraineeByStatusLocation(status, location);
		Log4J.getLogger().info("testCountTraineeByStatusLocationUC5 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status="All", location="Location1".
	 */
	@Test
	void testCountTraineeByStatusLocationUC6() {
		status = "All";
		location = "Location1";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(traineeRepository.countTraineeByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Empty", 0));
		List<DashboardVo> listActual = traineeService.countTraineeByStatusLocation(status, location);
		Log4J.getLogger().info("testCountTraineeByStatusLocationUC6 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status="All", location="All".
	 */
	@Test
	void testCountTraineeByStatusLocationUC7() {
		status = "All";
		location = "All";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(traineeRepository.countTraineeByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Empty", 0));
		List<DashboardVo> listActual = traineeService.countTraineeByStatusLocation(status, location);
		Log4J.getLogger().info("testCountTraineeByStatusLocationUC7 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status=null, location="Location1".
	 */
	@Test
	void testCountTraineeByStatusLocationUC8() {
		status = null;
		location = "Location1";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(traineeRepository.countTraineeByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Empty", 0));
		List<DashboardVo> listActual = traineeService.countTraineeByStatusLocation(status, location);
		Log4J.getLogger().info("testCountTraineeByStatusLocationUC8 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status=null, location=null.
	 */
	@Test
	void testCountTraineeByStatusLocationUC9() {
		status = null;
		location = null;
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(traineeRepository.countTraineeByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Empty", 0));
		List<DashboardVo> listActual = traineeService.countTraineeByStatusLocation(status, location);
		Log4J.getLogger().info("testCountTraineeByStatusLocationUC9 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status="", location="".
	 */
	@Test
	void testCountTraineeByStatusLocationUC10() {
		status = "";
		location = "";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(traineeRepository.countTraineeByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Empty", 0));
		List<DashboardVo> listActual = traineeService.countTraineeByStatusLocation(status, location);
		Log4J.getLogger().info("testCountTraineeByStatusLocationUC10 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database is empty, status="Enrolled", location="Location1".
	 */
	@Test
	void testGetListsCountTraineeByStatusLocationUC1() {
		status = "Enrolled";
		location = "Location1";

		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(traineeRepository.countTraineeByStatusLocation(status, location)).thenReturn(resultRepository);

		ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
		List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
		listVoInExpected.add(new DashboardVo(status, "Empty", 0));
		listExpected.add(listVoInExpected);

		ArrayList<List<DashboardVo>> listActual = traineeService.getListsCountTraineeByStatusLocation(status,
				location);
		Log4J.getLogger().info("testGetListsCountTraineeByStatusLocationUC1 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 5 record, status="Enrolled", location="Location1".
	 */
	@Test
	void testGetListsCountTraineeByStatusLocationUC2() {
		status = "Enrolled";
		location = "Location1";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		Object[] queryReturn = { "Java", 3 };
		resultRepository.add(queryReturn);
		when(traineeRepository.countTraineeByStatusLocation(status, location)).thenReturn(resultRepository);
		ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
		List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
		listVoInExpected.add(new DashboardVo(status, "Java", 3));
		listExpected.add(listVoInExpected);
		ArrayList<List<DashboardVo>> listActual = traineeService.getListsCountTraineeByStatusLocation(status,
				location);
		Log4J.getLogger().info("testGetListsCountTraineeByStatusLocationUC2 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 5 record, status="Enrolled", location="All".
	 */
	@Test
	void testGetListsCountTraineeByStatusLocationUC3() {
		status = "Enrolled";
		location = "All";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		Object[] queryReturn = { "Java", 4 };
		resultRepository.add(queryReturn);
		when(traineeRepository.countTraineeByStatusLocation(status, "%%")).thenReturn(resultRepository);
		ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
		List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
		listVoInExpected.add(new DashboardVo(status, "Java", 4));
		listExpected.add(listVoInExpected);

		ArrayList<List<DashboardVo>> listActual = traineeService.getListsCountTraineeByStatusLocation(status,
				location);
		Log4J.getLogger().info("testGetListsCountTraineeByStatusLocationUC3 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 5 record, status="Enrolled", location=null.
	 */
	@Test
	void testGetListsCountTraineeByStatusLocationUC4() {
		status = "Enrolled";
		location = null;
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(traineeRepository.countTraineeByStatusLocation(status, location)).thenReturn(resultRepository);
		ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
		List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
		listVoInExpected.add(new DashboardVo(status, "Empty", 0));
		listExpected.add(listVoInExpected);

		ArrayList<List<DashboardVo>> listActual = traineeService.getListsCountTraineeByStatusLocation(status,
				location);
		Log4J.getLogger().info("testGetListsCountTraineeByStatusLocationUC4 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 5 record, status="Enrolled", location="".
	 */
	@Test
	void testGetListsCountTraineeByStatusLocationUC5() {
		status = "Enrolled";
		location = "";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(traineeRepository.countTraineeByStatusLocation(status, location)).thenReturn(resultRepository);
		ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
		List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
		listVoInExpected.add(new DashboardVo(status, "Empty", 0));
		listExpected.add(listVoInExpected);
		ArrayList<List<DashboardVo>> listActual = traineeService.getListsCountTraineeByStatusLocation(status,
				location);
		Log4J.getLogger().info("testGetListsCountTraineeByStatusLocationUC5 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}
}
