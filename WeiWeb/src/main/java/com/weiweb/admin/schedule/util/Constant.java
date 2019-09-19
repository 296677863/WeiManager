package com.weiweb.admin.schedule.util;
/** 常量
 * 
 * @date 2016年11月15日 下午1:23:52 */
public class Constant {
	
	/** 定时任务状态
	 * 
	 */
	public enum ScheduleStatus {
		/** 正常 */
		NORMAL(0),
		/** 暂停 */
		PAUSE(1);
		
		private int value;
		
		private ScheduleStatus(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
}
