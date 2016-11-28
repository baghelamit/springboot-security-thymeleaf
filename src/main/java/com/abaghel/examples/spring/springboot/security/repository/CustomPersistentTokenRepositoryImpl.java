package com.abaghel.examples.spring.springboot.security.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;

import com.abaghel.examples.spring.springboot.security.entity.RememberMeToken;

/**
 * 
 * @author abaghel
 *
 */
@Repository
public class CustomPersistentTokenRepositoryImpl implements PersistentTokenRepository {

	@Autowired
	RememberMeTokenRepository rememberMeTokenRepository;

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		RememberMeToken rmToken = new RememberMeToken();
		rmToken.setSeries(token.getSeries());
		rmToken.setUserName(token.getUsername());
		rmToken.setTokenValue(token.getTokenValue());
		rmToken.setDate(token.getDate());
		rememberMeTokenRepository.save(rmToken);
	}

	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		RememberMeToken existingToken = this.rememberMeTokenRepository.findBySeries(series);
		if (existingToken != null) {
			existingToken.setTokenValue(tokenValue);
			existingToken.setDate(lastUsed);
			rememberMeTokenRepository.save(existingToken);
		}
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		PersistentRememberMeToken persistentRememberMeToken = null;
		RememberMeToken existingToken = this.rememberMeTokenRepository.findBySeries(seriesId);
		if (existingToken != null) {
			persistentRememberMeToken = new PersistentRememberMeToken(existingToken.getUserName(),existingToken.getSeries(), existingToken.getTokenValue(), existingToken.getDate());
		}
		return persistentRememberMeToken;
	}

	@Override
	public void removeUserTokens(String username) {
		List<RememberMeToken> tokens = rememberMeTokenRepository.findByUserName(username);
		rememberMeTokenRepository.delete(tokens);
	}
}
