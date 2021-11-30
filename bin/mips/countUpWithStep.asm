# This program asks the user for a lower boundary, higher boundary, 
# and step size. It then prints out all the numbers between the 
# two boundaries by incrementing the stepsize from the low until it 
# reaches the high. 
# 
# @author John Zeng
# 11/19/2021
    .data 
        lowMsg: .asciiz "Please enter the lower boundary." 
        highMsg: .asciiz "Please enter the higher boundary."
        stepMsg: .asciiz "Please enter the step size." 
        whiteSpace: .asciiz ", "
    .text 0x00400000
    .globl main 
main: 
    li $v0, 4
    la $a0, lowMsg
    syscall 
    li $v0, 5
    syscall
    move $t0, $v0   # ask user for low and store it into $t0

    li $v0, 4
    la $a0, highMsg
    syscall 
    li $v0, 5
    syscall
    move $t1, $v0   # ask user for high and store it into $t1

    li $v0, 4
    la $a0, stepMsg
    syscall 
    li $v0, 5
    syscall
    move $t2, $v0   # ask user for step size  and store it into $t2

    bgt $t0, $t1, end 
    loop: 
        li $v0, 1
        move $a0, $t0       # put value of the counter into $a0          
        syscall 
        add $t0, $t0, $t2   # increment the counter by the step size   
        bgt $t0, $t1, end   # if the counter is bigger than high boundary then end program
        li $v0, 4
        la $a0, whiteSpace
        syscall             # add comma and space 
        j loop 

    end: 
        li $v0, 10
        syscall 