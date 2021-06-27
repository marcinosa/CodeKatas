/**
 * Copyright 2021 BNYMellon. All rights reserved.
 * Use of this source code is governed by a BSD-style
 * license that can be found in the LICENSE file.
 */

package bnymellon.codekatas.calendarkata10;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

import org.eclipse.collections.api.RichIterable;
import org.eclipse.collections.api.block.function.Function2;
import org.eclipse.collections.api.block.predicate.Predicate2;
import org.eclipse.collections.api.list.MutableList;
import org.eclipse.collections.api.multimap.sortedset.MutableSortedSetMultimap;
import org.eclipse.collections.api.set.sorted.SortedSetIterable;
import org.eclipse.collections.impl.factory.Lists;
import org.eclipse.collections.impl.factory.Multimaps;
import org.threeten.extra.Interval;

public class MyCalendar
{
    private TimeZone timezone = TimeZone.getDefault();
    private MutableSortedSetMultimap<LocalDate, Meeting> meetings;

    public MyCalendar(TimeZone timezone)
    {
        this.timezone = timezone;
        this.meetings = Multimaps.mutable.sortedSet.with(Meeting.COMPARATOR);
    }

    public ZoneId getZoneId()
    {
        return this.timezone.toZoneId();
    }

    public FullMonth getMeetingsForYearMonth(int year, Month month)
    {
        return new FullMonth(LocalDate.of(year, month, 1), this.meetings);
    }

    public SortedSetIterable<Meeting> getMeetingsForDate(LocalDate date)
    {
        var sortedSet = this.meetings.get(date);
        return sortedSet;
    }

    public WorkWeek getMeetingsForWorkWeekOf(LocalDate value)
    {
        return new WorkWeek(value, this.meetings);
    }

    public FullWeek getMeetingsForFullWeekOf(LocalDate value)
    {
        return new FullWeek(value, this.meetings);
    }

    public boolean addMeeting(String subject, LocalDate date, LocalTime startTime, Duration duration)
    {
        if (!this.hasOverlappingMeeting(date, startTime, duration))
        {
            var meeting = new Meeting(subject, date, startTime, duration, this.getZoneId());
            return this.meetings.put(date, meeting);
        }
        return false;
    }

    /**
     * TODO Implement this method.
     *
     * Hint: Look at {@link Meeting#getInterval()}
     * Hint: Look at {@link Interval#overlaps(Interval)}
     * Hint: Look at {@link MyCalendar#getMeetingsForDate(LocalDate)}
     * Hint: Look at {@link RichIterable#anySatisfyWith(Predicate2, Object)}
     */
    public boolean hasOverlappingMeeting(LocalDate date, LocalTime startTime, Duration duration)
    {
        return false;
    }

    /**
     * TODO Implement this method.  Available timeslots include all times from {@link LocalTime#MIN}
     * and between meetings and up to {@link LocalTime#MAX}
     *
     * Hint: Look at {@link MyCalendar#getMeetingsForDate(LocalDate)}
     * Hint: Look at {@link RichIterable#injectInto(Object, Function2)}
     * Hint: Look at {@link LocalDate#atTime(LocalTime)}
     * Hint: Look at {@link LocalDateTime#atZone(ZoneId)}
     * Hint: Look at {@link ZonedDateTime#toInstant()}
     * Hint: Look at {@link Interval#of(Instant, Duration)}
     */
    public MutableList<Interval> getAvailableTimeslots(LocalDate date)
    {
        return Lists.mutable.empty();
    }

    @Override
    public String toString()
    {
        return "MyCalendar(" +
                "meetings=" + this.meetings +
                ')';
    }
}
