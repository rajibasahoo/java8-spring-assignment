package java8;

public class Employee {
	
	private int Id;
    private String name;
    private long sal;

    @Override
	public String toString() {
		return "Employee [Id=" + Id + ", name=" + name + ", sal=" + sal + "]";
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getSal() {
		return sal;
	}

	public void setSal(long sal) {
		this.sal = sal;
	}

	public Employee(int id, String name, long sal) {
        Id = id;
        this.name = name;
        this.sal = sal;
    }

}
