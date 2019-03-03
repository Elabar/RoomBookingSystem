
public class User {
	private String name;
	private String member_type;
	private boolean excl_reward;
	
	public String getMember_type() {
		return member_type;
	}
	
	public boolean getExcl_reward() {
		return excl_reward;
	}

	public void setExcl_reward(boolean excl_reward) {
		this.excl_reward = excl_reward;
	}

	public User(String name,String member_type) {
		this.name = name;
		this.member_type = member_type;
	}

	

	
	
}
