  /**
* Copyright © The Bank of New York Mellon 2021
* Licensed under the BSD-3-Clause License (the “License”);
* You may not use this file except in compliance with the License.
* You may obtain a copy of the License at:
* https://opensource.org/licenses/BSD-3-Clause
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES ARE DISCLAIMED.  SEE THE LICENSE FOR FURTHER RESTRICTIONS.
*/

package bnymellon.codekatas.deckofcards.custom.collections;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Bag<T> extends RichIterable<T>
{
    int sizeDistinct();

    @Override
    Bag<T> filter(Predicate<? super T> predicate);

    @Override
    Bag<T> filterNot(Predicate<? super T> predicate);

    @Override
    <V> Bag<V> map(Function<? super T, ? extends V> function);

    @Override
    <V> Bag<V> flatMap(Function<? super T, ? extends Iterable<V>> function);

    @Override
    default Bag<T> peek(Consumer<? super T> consumer)
    {
        this.forEach(consumer);
        return this;
    }

    int getOccurrences(T element);

    void forEachWithOccurrences(BiConsumer<? super T, Integer> procedure);
}
