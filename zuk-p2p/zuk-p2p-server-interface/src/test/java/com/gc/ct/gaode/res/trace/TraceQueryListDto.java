/**
 * 
 */
package com.gc.ct.gaode.res.trace;

import java.util.List;

/**
 * @author:  大聊
 * @Package:  com.gc.ct.gaode.res.trace
 * @ClassName:  TraceQueryListDto
 * @Description:  一句话描述该类的功能
 * @date:  2019年4月24日 下午10:25:08
 * @email: 513283439@qq.com
 */
public class TraceQueryListDto {
	
	private Long counts;
	private List<TrackBean> tracks;
	
	public Long getCounts() {
		return counts;
	}
	public void setCounts(Long counts) {
		this.counts = counts;
	}
	public List<TrackBean> getTracks() {
		return tracks;
	}
	public void setTracks(List<TrackBean> tracks) {
		this.tracks = tracks;
	}

	public static class TrackBean{
		private Long counts;
		private Long distance;
		private List<PointBean> points;
		private Long time;
		private Long trid;
		public Long getCounts() {
			return counts;
		}
		public void setCounts(Long counts) {
			this.counts = counts;
		}
		public Long getDistance() {
			return distance;
		}
		public void setDistance(Long distance) {
			this.distance = distance;
		}
		public List<PointBean> getPoints() {
			return points;
		}
		public void setPoints(List<PointBean> points) {
			this.points = points;
		}
		public Long getTime() {
			return time;
		}
		public void setTime(Long time) {
			this.time = time;
		}
		public Long getTrid() {
			return trid;
		}
		public void setTrid(Long trid) {
			this.trid = trid;
		}
		
	}
	
	
	public static class PointBean{
		private Long accuracy;
		private Long direction;
		private Long height;
		private Long locatetime;
		private Long speed;
		private String location;
		public Long getAccuracy() {
			return accuracy;
		}
		public void setAccuracy(Long accuracy) {
			this.accuracy = accuracy;
		}
		public Long getDirection() {
			return direction;
		}
		public void setDirection(Long direction) {
			this.direction = direction;
		}
		public Long getHeight() {
			return height;
		}
		public void setHeight(Long height) {
			this.height = height;
		}
		public Long getLocatetime() {
			return locatetime;
		}
		public void setLocatetime(Long locatetime) {
			this.locatetime = locatetime;
		}
		public Long getSpeed() {
			return speed;
		}
		public void setSpeed(Long speed) {
			this.speed = speed;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		
	}

}
