# tek-collections

When I took Computer Science was datastructures, how I love making my own LinkedLists in c++. Java 
collections are great, well... I use them all the time and they work. However, I feel
they suffer from a few design flaws:

1) Optional methods: A class may have iterator that may have a remove()
2) Varying support for null: HashSet will accept null TreeSet will not
3) No interface(s) for Immutable collections: Collections.immutableList(List) returns a List with .add(X)
4) Interfaces have two many methods (many of them are not commonly used)
5) Primative types like int/long/double need third party libraries with other collections
6) 'Off Heap'/Garbage collector avoidance implementations implemented in third party libraries
7) More exotic data structures splaytree/trie/bloomfilters are also go elsewhere

The list could be longer, but not to rattle on about complaints. The point again is while
good they surely could be better. 

## Mission

1) Up the capatability between different datastructures out there
2) Provide some missing datastructures with good implementations 
3) Define APIs that things seemless
4) Tackle the problems above, and others, and find new ones. 

## But what about...

Look I am sure I am not breaking new ground. There are lots of github(s) floating around 
with cool stuff. I am not the first to realize java is without a MultiMap, but I am not
advocating that Tek-Collections multimap will be the best eva! The goal is more of the API
focus. EX. Map.get() returns null, so we need another method map.getOptional(x) or 
whatever we need.

### The Null Problem

I find the mismatched support of null to be the most frustrating thing. A big key to swapping
an implementation is compatibility. I started by creating wrapper classes to make things 
work well.

    @ParameterizedTest
    @MethodSource("nullSupportingSets")
    void test(java.util.Set s){
        s.add(null);
        Assertions.assertTrue(s.contains(null));
    }
    private static Stream<Set> nullSupportingSets(){
        //TreeSet here would fail but not 
        NullRetroFitSortedTest t = new NullRetroFitSortedTest();
        java.util.HashSet s = new HashSet();
        return Stream.of(t, s);
    }
