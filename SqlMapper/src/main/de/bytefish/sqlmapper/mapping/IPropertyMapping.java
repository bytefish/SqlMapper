// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.mapping;

import java.sql.ResultSet;

public interface IPropertyMapping<TEntity> {

    void map(final TEntity entity, final ResultSet resultSet);

}
