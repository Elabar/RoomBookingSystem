import static org.junit.Assert.*;
import org.junit.Test;

public class TestBooking {

	//test1
	@Test
	public void testSetBooking() {
		User user = new User("LEE","VIP");
		assertEquals("VIP", user.getMember_type());
	}
}
