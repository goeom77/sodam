package com.samsung.sodam.db.repository.counselor;

/**
 * A Projection for the {@link com.samsung.sodam.db.entity.Enterprise} entity
 */
public interface EnterpriseInfo {
    int getEnterpriseId();

    String getEnterprise();

    String getManagerId();

    String getCorporateCode();
}