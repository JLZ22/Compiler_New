    .text 0x00400000
    .globl main
main: 
    li $v0, 4                   # Configure accumulator to print a string
    la $a0, msg                 # Store msg into the $a0 register
    syscall 
    li $v0, 10                  # Terminate the program 
    syscall 

    .data 
msg: 
    .asciiz "Hello World!\n"    # Store "Hello World!" into the msg label 