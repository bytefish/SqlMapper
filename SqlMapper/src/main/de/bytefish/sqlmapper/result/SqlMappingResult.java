// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.result;

import java.util.Optional;

public class SqlMappingResult<TEntity> {

    private Optional<TEntity> entity;
    private Optional<SqlMappingError> error;

    public SqlMappingResult(TEntity entity) {
        this.entity = Optional.of(entity);
        this.error = Optional.empty();
    }

    public SqlMappingResult(SqlMappingError error) {
        this.error = Optional.of(error);
        this.entity = Optional.empty();
    }

    public Optional<TEntity> getEntity() {
        return entity;
    }

    public Optional<SqlMappingError> getError() {
        return error;
    }

    public boolean isValid() {
        return !error.isPresent();
    }
}