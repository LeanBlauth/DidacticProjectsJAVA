package entities;

import java.util.Date;

public class LogEntry {

	private String userName;
	private Date accessTime;
	
	public LogEntry(String userName, Date accessTime) {
		this.userName = userName;
		this.accessTime = accessTime;
	}

	public String getUserName() {
		return userName;
	}

	public Date getAccessTime() {
		return accessTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LogEntry other = (LogEntry) obj;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
}
