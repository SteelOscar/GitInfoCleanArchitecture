package ru.steeloscar.gitinfocleanarchitecture.commons

interface BaseMapper<in A, out B> {


    interface OneWayMapper<in A, out B> {
        fun map(type: A): B
    }

    interface TwoWayMapper<A, B> {
        fun forwardMap(type: A): B
        fun reverseMap(type: B): A
    }
}