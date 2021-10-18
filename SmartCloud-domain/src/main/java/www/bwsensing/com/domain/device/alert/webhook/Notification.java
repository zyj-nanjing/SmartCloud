package www.bwsensing.com.domain.device.alert.webhook;

import java.util.Date;

/**
 * @author macos-zyj
 */
public class Notification {

	private String status;
	private Date time;
	private String summary;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Notification() {
		// TODO Auto-generated constructor stub
	}

	public Notification(String status, Date time, String summary) {
		super();
		this.status = status;
		this.time = time;
		this.summary = summary;
	}

	@Override
	public String toString() {
		return "Notification [status=" + status + ", time=" + time + ", summary=" + summary + "]";
	}

}
