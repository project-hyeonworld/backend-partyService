package io.partyservice.api.party.infrastructure.jdbc;

import io.partyservice.api.party.infrastructure.entity.PartyEntity;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 6.
 */
@Repository
@RequiredArgsConstructor
public class PartyJdbcTemplateRepositoryImpl implements PartyJdbcTemplateRepository {
  private final JdbcTemplate jdbcTemplate;

  public PartyEntity insert(PartyEntity party) {
    String sql = "INSERT INTO party (relation_type, created_at) " +
        "VALUES (?, ?)";
    KeyHolder keyHolder = new GeneratedKeyHolder();

    jdbcTemplate.update(connection -> {
      PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
      ps.setObject(1, party.getRelationType());
      ps.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
      return ps;
    }, keyHolder);

    Number key = keyHolder.getKey();
    if (key != null) {
      party.setId(key.longValue());
    }
    return party;
  }
  public PartyEntity update(PartyEntity party) {
    StringBuilder sql = new StringBuilder("UPDATE party SET");
    List<Object> params = new ArrayList<>();
    boolean needComma = false;

    sql.append(" relation_type = ?");
    params.add(party.getRelationType());
    needComma = true;

    if (party.getTerminatedAt() != null) {
      if (needComma) {
        sql.append(",");
      }
      sql.append(" terminated_at = ?");
      params.add(party.getTerminatedAt());
      needComma = true;
    }
    if (params.isEmpty()) {
      return party;
    }

    sql.append(" WHERE id = ?");
    params.add(party.getId());
    try {
      int updatedRows = jdbcTemplate.update(sql.toString(),
          params.toArray());

      if (updatedRows == 0) {
      }
      return party;
    } catch (DataAccessException e) {
      throw new RuntimeException("Failed to update party", e);
    }
  }

  public PartyEntity save(PartyEntity party) {
    return Optional.ofNullable(party.getId())
        .map(id -> update(party))
        .orElseGet(() -> insert(party));
  }

  @Override
  public Optional<PartyEntity> findById(long partyId) {
    return Optional.empty();
  }

  public Optional<Long> findIdByRelationType(int relationType) {
    String sql = "SELECT id FROM party WHERE relation_type = ? AND terminated_at IS NULL ORDER BY created_at DESC LIMIT 1";
    try {
      return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new Object[]{relationType}, Long.class));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }

  }

  @Override
  public void terminateAll() {
    String sql = "UPDATE party SET terminated_at = NOW() WHERE terminated_at IS NULL";
    int updatedRows = jdbcTemplate.update(sql);
  }

  @Override
  public Optional<Long> findIdByHouseHold() {
    String sql = "SELECT id FROM party WHERE (relation_type = 1 OR relation_type = 2 OR relation_type = 3)  AND terminated_at IS NULL ORDER BY created_at DESC LIMIT 1";
    try {
      return Optional.ofNullable(jdbcTemplate.queryForObject(sql, Long.class));
    } catch (EmptyResultDataAccessException e) {
      return Optional.empty();
    }
  }
}
