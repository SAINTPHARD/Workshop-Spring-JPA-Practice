package com.demonstracao.course.entities.enums; // (Crie este pacote se não existir)

public enum OrderStatus {
	// ENUM PARA STATUS DO PEDIDO COM ENUMERAÇÕES
	WAITING_PAYMENT(1),
	PAID(2),
	SHIPPED(3),
	DELIVERED(4),
	CANCELED(5);
	
	// 2. Crie um campo para armazenar o código
	private int code;
	
	// 3. Crie um construtor para o enum
	private OrderStatus(int code) {
		this.code = code;
	}
	
	// 4. Crie um método público para expor o código
	public int getCode() {
		return code;
	}
	
	// 5. Crie um método estático para converter um código de volta para o Enum
	public static OrderStatus valueOf(int code) {
		for (OrderStatus value : OrderStatus.values()) {
			if (value.getCode() == code) {
				return value;
			}
		}
		throw new IllegalArgumentException("Invalid OrderStatus code");
	}
	
}