// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.result;

public class SqlMappingResult<TEntity> {

    private final TEntity entity;
    private final SqlMappingError error;

    public SqlMappingResult(TEntity entity) {
        this.entity = entity;
        this.error = null;
    }

    public SqlMappingResult(SqlMappingError error) {
        this.entity = null;
        this.error = error;
    }

    public TEntity getResult() {
        return entity;
    }

    public SqlMappingError getError() {
        return error;
    }

    public boolean isValid() {
        return error == null;
    }

}