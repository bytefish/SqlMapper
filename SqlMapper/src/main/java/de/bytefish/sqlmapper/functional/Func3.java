// Copyright (c) Philipp Wagner. All rights reserved.
// Licensed under the MIT license. See LICENSE file in the project root for full license information.

package de.bytefish.sqlmapper.functional;

@FunctionalInterface
public interface Func3<I0, I1, O> {
    O invoke(I0 i0, I1 i1) throws Exception;
}