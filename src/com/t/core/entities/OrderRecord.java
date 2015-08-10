package com.t.core.entities;

import java.sql.Timestamp;

public class OrderRecord {
		private Integer recordId;
		private Integer cartId;
		private Integer itemId;
		private Integer number;
		private Timestamp recordTime;
		public OrderRecord(){}
		public OrderRecord(Integer recordId, Integer cartId, Integer itemId,
				Integer number, Timestamp recordTime) {
			this.recordId = recordId;
			this.cartId = cartId;
			this.itemId = itemId;
			this.number = number;
			this.recordTime = recordTime;
		}

		public Integer getRecordId() {
			return recordId;
		}
		public void setRecordId(Integer recordId) {
			this.recordId = recordId;
		}
		public Integer getCartId() {
			return cartId;
		}
		public void setCartId(Integer cartId) {
			this.cartId = cartId;
		}
		public Integer getItemId() {
			return itemId;
		}
		public void setItemId(Integer itemId) {
			this.itemId = itemId;
		}
		public Integer getNumber() {
			return number;
		}
		public void setNumber(Integer number) {
			this.number = number;
		}
		public Timestamp getRecordTime() {
			return recordTime;
		}
		public void setRecordTime(Timestamp recordTime) {
			this.recordTime = recordTime;
		}
		
}
