/*
 * $Id: LocaTable.java,v 1.3 2009-03-15 12:33:17 tomoke Exp $
 *
 * Copyright 2004 Sun Microsystems, Inc., 4150 Network Circle,
 * Santa Clara, California 95054, U.S.A. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

package com.sun.pdfview.font.ttf;

import java.nio.ByteBuffer;

/**
 * Model the TrueType Loca table
 */
public class LocaTable extends TrueTypeTable {
    /** if true, the table stores glyphs in long format */
    private boolean isLong;

    /** the offsets themselves */
    private int offsets[];

    /** Creates a new instance of HmtxTable */
    protected LocaTable(TrueTypeFont ttf) {
        super (TrueTypeTable.LOCA_TABLE);

        MaxpTable maxp = (MaxpTable) ttf.getTable("maxp");
        int numGlyphs = maxp.getNumGlyphs();

        HeadTable head = (HeadTable) ttf.getTable("head");
        short format = head.getIndexToLocFormat();
        isLong = (format == 1);

        offsets = new int[numGlyphs + 1];
    }

    /**
     * get the offset, in bytes, of a given glyph from the start of
     * the glyph table
     */
    public int getOffset(int glyphID) {
        return offsets[glyphID];
    }

    /**
     * get the size, in bytes, of the given glyph
     */
    public int getSize(int glyphID) {
        return offsets[glyphID + 1] - offsets[glyphID];
    }

    /**
     * Return true if the glyphs arte in long (int) format, or
     * false if they are in short (short) format
     */
    public boolean isLongFormat() {
        return isLong;
    }


    /** get the data in this map as a ByteBuffer */
    public ByteBuffer getData() {
        int size = getLength();

        ByteBuffer buf = ByteBuffer.allocate(size);

        // write the offsets
        for (int i = 0; i < offsets.length; i++) {
            if (isLongFormat()) {
                buf.putInt(offsets[i]);
            } else {
                buf.putShort((short) (offsets[i] / 2));
            }
        }

        // reset the start pointer
        buf.flip();

        return buf;
    }

    /** Initialize this structure from a ByteBuffer */
    public void setData(ByteBuffer data) {
        for (int i = 0; i < offsets.length; i++) {
            if (isLongFormat()) {
                offsets[i] = data.getInt();
            } else {
                offsets[i] = 2 * ( 0xFFFF & (int) data.getShort());
            }
        }
    }

    /**
     * Get the length of this table
     */
    public int getLength() {
        if (isLongFormat()) {
            return offsets.length * 4;
        } else {
            return offsets.length * 2;
        }
    }
}
