#!/bin/bash

# Set this to Jira key
jira_key="XX"

# Create the description from the 2nd argument through to the last by replacing spaces with -
new_branch_desc=$(echo "${@:2}" | awk '{gsub(/ /,"-")}1')

git switch --create "$jira_key$1-$new_branch_desc"
