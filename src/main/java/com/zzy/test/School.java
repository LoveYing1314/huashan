package com.zzy.test;

public enum School {
	
	�ɶ���Ϣ���̴�ѧ(1),�Ĵ���ѧ(2);
	
	private int id;

	private School(int id) {
		this.id = id;
	}
	
	public static String getName(int id) {  
        for (School c : School.values()) {  
            if (c.getId() == id) {  
                return c.name();  
            }  
        }  
        return "111";  
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}  
	
}

