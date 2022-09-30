package es.library.databaseinspanish.model.security;

import java.util.Objects;

import es.library.databaseinspanish.model.Model;

public class TokenPair implements Model {

	private String accessToken;
	private String refreshToken;
	
	public TokenPair() {}
	
	public TokenPair(String accessToken, String refreshToken) {
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
	}

	public String getAccessToken() {return accessToken;}
	public void setAccessToken(String accessToken) {this.accessToken = accessToken;}

	public String getRefreshToken() {return refreshToken;}
	public void setRefreshToken(String refreshToken) {this.refreshToken = refreshToken;}

	@Override
	public int hashCode() {
		return Objects.hash(accessToken, refreshToken);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TokenPair other = (TokenPair) obj;
		return Objects.equals(accessToken, other.accessToken) && Objects.equals(refreshToken, other.refreshToken);
	}

	@Override
	public String toString() {
		return "TokenPair [accessToken=" + accessToken + ", refreshToken=" + refreshToken + "]";
	}
	
}
