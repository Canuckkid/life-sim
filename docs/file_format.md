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
