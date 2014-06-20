/*
 * -----------------------------------------------------------------------
 * Copyright © 2013-2014 Meno Hochschild, <http://www.menodata.de/>
 * -----------------------------------------------------------------------
 * This file (AmPmElement.java) is part of project Time4J.
 *
 * Time4J is free software: You can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published
 * by the Free Software Foundation, either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * Time4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Time4J. If not, see <http://www.gnu.org/licenses/>.
 * -----------------------------------------------------------------------
 */

package net.time4j;

import net.time4j.engine.AttributeQuery;
import net.time4j.engine.ChronoEntity;
import net.time4j.format.Attributes;
import net.time4j.format.CalendarText;
import net.time4j.format.ParseLog;
import net.time4j.format.TextElement;
import net.time4j.format.TextWidth;

import java.io.IOException;
import java.util.Locale;

import static net.time4j.format.CalendarText.ISO_CALENDAR_TYPE;


/**
 * <p>Repr&auml;sentiert das Halbtagselement. </p>
 *
 * @author      Meno Hochschild
 * @concurrency <immutable>
 */
enum AmPmElement
    implements TextElement<Meridiem> {

    //~ Statische Felder/Initialisierungen --------------------------------

    AM_PM_OF_DAY;

    //~ Methoden ----------------------------------------------------------

    @Override
    public Class<Meridiem> getType() {

        return Meridiem.class;

    }

    @Override
    public char getSymbol() {

        return 'a';

    }

    @Override
    public int compare(
        ChronoEntity<?> o1,
        ChronoEntity<?> o2
    ) {

        return o1.get(this).compareTo(o2.get(this));

    }

    @Override
    public Meridiem getDefaultMinimum() {

        return Meridiem.AM;

    }

    @Override
    public Meridiem getDefaultMaximum() {

        return Meridiem.PM;

    }

    @Override
    public boolean isDateElement() {

        return false;

    }

    @Override
    public boolean isTimeElement() {

        return true;

    }

    @Override
    public boolean isLenient() {

        return false;

    }

    @Override
    public void print(
        ChronoEntity<?> context,
        Appendable buffer,
        AttributeQuery attributes
    ) throws IOException {

        buffer.append(this.accessor(attributes).print(context.get(this)));

    }

    @Override
    public Meridiem parse(
        CharSequence text,
        ParseLog status,
        AttributeQuery attributes
    ) {

        return this.accessor(attributes).parse(
            text,
            status,
            this.getType(),
            attributes
        );

    }

    private CalendarText.Accessor accessor(AttributeQuery attributes) {

        CalendarText cnames =
            CalendarText.getInstance(
                attributes.get(Attributes.CALENDAR_TYPE, ISO_CALENDAR_TYPE),
                attributes.get(Attributes.LANGUAGE, Locale.ROOT));

        TextWidth textWidth =
            attributes.get(Attributes.TEXT_WIDTH, TextWidth.WIDE);

        return cnames.getMeridiems(textWidth);

    }

}
