Demo project that shows an issue in spring mongodb.

Mongo repository throws DuplicateKeyException when I use `save()` method with object that was lazily loaded
from `@DocumentReference`

Failing test demonstrating the bug is here `./src/test/java/com/example/demo/Demo1ApplicationTests.java`