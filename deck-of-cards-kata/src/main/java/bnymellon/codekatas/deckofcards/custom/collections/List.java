  /**
* Copyright © The Bank of New York Mellon 2021
* Licensed under the BSD-3-Clause License (the “License”);
* You may not use this file except in compliance with the License.
* You may obtain a copy of the License at:
* https://opensource.org/licenses/BSD-3-Clause
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES ARE DISCLAIMED.  SEE THE LICENSE FOR FURTHER RESTRICTIONS.
*/

package bnymellon.codekatas.deckofcards.custom.collections;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface List<T> extends RichIterable<T>
{
    @Override
    List<T> filter(Predicate<? super T> predicate);

    @Override
    List<T> filterNot(Predicate<? super T> predicate);

    @Override
    <V> List<V> map(Function<? super T, ? extends V> function);

    @Override
    <V> List<V> flatMap(Function<? super T, ? extends Iterable<V>> function);

    @Override
    default List<T> peek(Consumer<? super T> consumer)
    {
        this.forEach(consumer);
        return this;
    }

    T get(int index);
}
