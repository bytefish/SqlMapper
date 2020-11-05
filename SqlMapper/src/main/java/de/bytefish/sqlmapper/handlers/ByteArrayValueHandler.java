// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.handlers;

import de.bytefish.sqlmapper.utils.BoxingUtils;

import java.lang.reflect.Type;
import java.sql.ResultSet;

public class ByteArrayValueHandler extends BaseValueHandler<Byte[]> {

    @Override
    protected Byte[] internalHandle(String columnName, ResultSet resultSet) throws Exception {
        return BoxingUtils.toObject(resultSet.getBytes(columnName));
    }

    @Override
    public Type getTargetType() {
        return Byte[].class;
    }

}
