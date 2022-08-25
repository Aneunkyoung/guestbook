package GuestBook;

public class GuestBookDTO {
	private String email;
	private String name;
	private int no;
	private String date;
	private String content;
	
	public GuestBookDTO() {
		// TODO Auto-generated constructor stub
	}
	public GuestBookDTO(String email) {
		super();
		this.email = email;
	}
	
	public GuestBookDTO(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}
	
	public GuestBookDTO(String email, String name, int no) {
		super();
		this.email = email;
		this.name = name;
		this.no = no;
	}
	public GuestBookDTO(String email, String name, int no, String date) {
		super();
		this.email = email;
		this.name = name;
		this.no = no;
		this.date = date;
	}
	public GuestBookDTO(String email, String name, int no, String date, String content) {
		super();
		this.email = email;
		this.name = name;
		this.no = no;
		this.date = date;
		this.content = content;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
