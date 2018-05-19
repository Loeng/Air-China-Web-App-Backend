/**
 * Copyright 2018 Steve Jrong - https://www.stevejrong.top
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.stevejrong.airchina.common.util;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

/**
 * Util - 日期时间工具类
 *
 * @author Steve Jrong
 * @since 1.0 create date: 2018年05月27日 下午12:22
 */
public final class DateTimeUtil {

    /**
     * 东八区
     */
    private static final String UTC_GMT8 = "+8";

    /**
     * 获取当前时间
     *
     * @return LocalDateTime对象
     */
    private static final LocalDateTime getNow() {
        return LocalDateTime.now();
    }

    /**
     * 获取在某时区下某一时间的时间戳
     *
     * @param dateTime LocalDateTime对象
     * @param utcDiff UTC时区差值
     * @return
     */
    private static final Long getTimestamp(LocalDateTime dateTime, String utcDiff) {
        return dateTime.toInstant(ZoneOffset.of(utcDiff)).toEpochMilli();
    }

    /**
     * （以中国所在的东八区为准）获取当前时间的时间戳
     *
     * @return Long类型的时间戳
     */
    public static Long getTimestampByNow() {
        return getTimestamp(getNow(), UTC_GMT8);
    }
}
