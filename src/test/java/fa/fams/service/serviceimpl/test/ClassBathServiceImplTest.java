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
import fa.fams.repository.ClassBathRepository;
import fa.fams.service.serviceimpl.ClassBathServiceImpl;

@SpringBootTest
class ClassBathServiceImplTest {
	@Mock
	private ClassBathRepository classBathRepository;

	@InjectMocks
	ClassBathServiceImpl classBathService;

	private String status;
	private String location;

	/**
	 * test when database is empty.
	 */
	@Test
	void testCountClassByStatusLocationUC1() {
		status = "Planning";
		location = "Location1";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(classBathRepository.countClassByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Empty", 0));
		List<DashboardVo> listActual = classBathService.countClassByStatusLocation(status, location);
		Log4J.getLogger().info("testCountClassByStatusLocationUC1 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status="Planning", location="Location1".
	 */
	@Test
	void testCountClassByStatusLocationUC2() {
		String status = "Planning";
		String location = "Location1";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		Object[] queryReturn = { "Java", 3 };
		resultRepository.add(queryReturn);
		when(classBathRepository.countClassByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Java", 3));
		List<DashboardVo> listActual = classBathService.countClassByStatusLocation(status, location);
		Log4J.getLogger().info("testCountClassByStatusLocationUC2 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status="Planning", location="All".
	 */
	@Test
	void testCountClassByStatusLocationUC3() {
		status = "Planning";
		location = "All";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		Object[] queryReturn = { "Java", 4 };
		resultRepository.add(queryReturn);
		when(classBathRepository.countClassByStatusLocation(status, "%%")).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Java", 4));
		List<DashboardVo> listActual = classBathService.countClassByStatusLocation(status, location);
		Log4J.getLogger().info("testCountClassByStatusLocationUC3 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status="Planning", location=null.
	 */
	@Test
	void testCountClassByStatusLocationUC4() {
		status = "Planning";
		location = null;
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(classBathRepository.countClassByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Empty", 0));
		List<DashboardVo> listActual = classBathService.countClassByStatusLocation(status, location);
		Log4J.getLogger().info("testCountClassByStatusLocationUC4 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status="Planning", location="".
	 */
	@Test
	void testCountClassByStatusLocationUC5() {
		status = "planning";
		location = "";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(classBathRepository.countClassByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Empty", 0));
		List<DashboardVo> listActual = classBathService.countClassByStatusLocation(status, location);
		Log4J.getLogger().info("testCountClassByStatusLocationUC5 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status="All", location="Location1".
	 */
	@Test
	void testCountClassByStatusLocationUC6() {
		status = "All";
		location = "Location1";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(classBathRepository.countClassByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Empty", 0));
		List<DashboardVo> listActual = classBathService.countClassByStatusLocation(status, location);
		Log4J.getLogger().info("testCountClassByStatusLocationUC6 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status="All", location="All".
	 */
	@Test
	void testCountClassByStatusLocationUC7() {
		status = "All";
		location = "All";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(classBathRepository.countClassByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Empty", 0));
		List<DashboardVo> listActual = classBathService.countClassByStatusLocation(status, location);
		Log4J.getLogger().info("testCountClassByStatusLocationUC7 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status=null, location="Location1".
	 */
	@Test
	void testCountClassByStatusLocationUC8() {
		status = null;
		location = "Location1";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(classBathRepository.countClassByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Empty", 0));
		List<DashboardVo> listActual = classBathService.countClassByStatusLocation(status, location);
		Log4J.getLogger().info("testCountClassByStatusLocationUC8 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status=null, location=null.
	 */
	@Test
	void testCountClassByStatusLocationUC9() {
		status = null;
		location = null;
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(classBathRepository.countClassByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Empty", 0));
		List<DashboardVo> listActual = classBathService.countClassByStatusLocation(status, location);
		Log4J.getLogger().info("testCountClassByStatusLocationUC9 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 4 record, status="", location="".
	 */
	@Test
	void testCountClassByStatusLocationUC10() {
		status = "";
		location = "";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(classBathRepository.countClassByStatusLocation(status, location)).thenReturn(resultRepository);
		List<DashboardVo> listExpected = new ArrayList<DashboardVo>();
		listExpected.add(new DashboardVo(status, "Empty", 0));
		List<DashboardVo> listActual = classBathService.countClassByStatusLocation(status, location);
		Log4J.getLogger().info("testCountClassByStatusLocationUC10 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database is empty, status="Planning", location="Location1".
	 */
	@Test
	void testGetListsCountClassByStatusLocationUC1() {
		status = "Planning";
		location = "Location1";

		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(classBathRepository.countClassByStatusLocation(status, location)).thenReturn(resultRepository);

		ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
		List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
		listVoInExpected.add(new DashboardVo(status, "Empty", 0));
		listExpected.add(listVoInExpected);

		ArrayList<List<DashboardVo>> listActual = classBathService.getListsCountClassByStatusLocation(status,
				location);
		Log4J.getLogger().info("testGetListsCountClassByStatusLocationUC1 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 5 record, status="Planning", location="Location1".
	 */
	@Test
	void testGetListsCountClassByStatusLocationUC2() {
		status = "Planning";
		location = "Location1";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		Object[] queryReturn = { "Java", 3 };
		resultRepository.add(queryReturn);
		when(classBathRepository.countClassByStatusLocation(status, location)).thenReturn(resultRepository);
		ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
		List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
		listVoInExpected.add(new DashboardVo(status, "Java", 3));
		listExpected.add(listVoInExpected);
		ArrayList<List<DashboardVo>> listActual = classBathService.getListsCountClassByStatusLocation(status,
				location);
		Log4J.getLogger().info("testGetListsCountClassByStatusLocationUC2 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 5 record, status="Planning", location="All".
	 */
	@Test
	void testGetListsCountClassByStatusLocationUC3() {
		status = "Planning";
		location = "All";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		Object[] queryReturn = { "Java", 4 };
		resultRepository.add(queryReturn);
		when(classBathRepository.countClassByStatusLocation(status, "%%")).thenReturn(resultRepository);
		ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
		List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
		listVoInExpected.add(new DashboardVo(status, "Java", 4));
		listExpected.add(listVoInExpected);

		ArrayList<List<DashboardVo>> listActual = classBathService.getListsCountClassByStatusLocation(status,
				location);
		Log4J.getLogger().info("testGetListAllByStatusLocationUC3 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 5 record, status="Planning", location=null.
	 */
	@Test
	void testGetListsCountClassByStatusLocationUC4() {
		status = "Planning";
		location = null;
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(classBathRepository.countClassByStatusLocation(status, location)).thenReturn(resultRepository);
		ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
		List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
		listVoInExpected.add(new DashboardVo(status, "Empty", 0));
		listExpected.add(listVoInExpected);

		ArrayList<List<DashboardVo>> listActual = classBathService.getListsCountClassByStatusLocation(status,
				location);
		Log4J.getLogger().info("testGetListsCountClassByStatusLocationUC4 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

	/**
	 * test when database has 5 record, status="Planning", location="".
	 */
	@Test
	void testGetListsCountClassByStatusLocationUC5() {
		status = "Planning";
		location = "";
		List<Object[]> resultRepository = new ArrayList<Object[]>();
		when(classBathRepository.countClassByStatusLocation(status, location)).thenReturn(resultRepository);
		ArrayList<List<DashboardVo>> listExpected = new ArrayList<List<DashboardVo>>();
		List<DashboardVo> listVoInExpected = new ArrayList<DashboardVo>();
		listVoInExpected.add(new DashboardVo(status, "Empty", 0));
		listExpected.add(listVoInExpected);
		ArrayList<List<DashboardVo>> listActual = classBathService.getListsCountClassByStatusLocation(status,
				location);
		Log4J.getLogger().info("testGetListsCountClassByStatusLocationUC5 actual:" + listActual);
		assertEquals(listExpected, listActual);
	}

}
