# File format for saved patterns
## Options
### JSON
- I like
- Human- and machine-readable/writable
- No builtin libraries

### CSV
- Machine-readable/writable
- Human unreadable/write-able
- Don't need builtin library

### Flat file
- Gives a good picture of the board's state
- Loses info about age, hunger level, etc.
- Pretty easy to read/write

### Serealizing objects
- Not sure how that works
- Not human-readable or writable

## CSV format
```
#row	col	type	age	food-level
0	0	f	3	15
3	3	s	2	14
```

- Each row contains a record for exactly one organism
- The first two columns are the row and column of the organism
  - Values must be non-negative integers
- The third column is the type of the organism:
  - `a -> Algae`
  - `s -> Shark`
  - `f -> Fish`
  - Other values are invalid
- The second and third columns are the age and food level of the
  organism.
  - Both these numbers must be positive integers
  - They must be less than or equal to the maximum age and food level
    of the species
- Columns can be separated by spaces (` `), tabs (`\t`), or commas
  (`,`)
- Behaviour when an invalid line is reached is undefined
