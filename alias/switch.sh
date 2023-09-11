#!/bin/bash

# Set this to Jira key
jira_key="XX"

# get the search term from the command line argument
search_term="$1"

# filter out remote branches that have a corresponding local tracking branch
# Get all branches | remove leading whitespace from each line | remove remote branches | find by ticket number
local_branches=$(git branch --all \
  | sed 's/^[ \t]*//' \
  | grep --invert-match "^remotes/origin" \
  | grep -Ei "$jira_key[-/]?$search_term([-/]|$)")
# Get all branches | find by ticket number
remote_branches=$(git branch --all | grep --ignore-case remotes/origin/$jira_key[-/]?\"$search_term\"[-/])

# create an array to store the branch names
branch_array=()

# add local branches to the array
while read -r branch_name; do
  if [ -n "$branch_name" ]; then
    branch_array+=("$branch_name")
  fi
done <<< "$local_branches"

# add remote branches that don't have locally tracked branches to the array
while read -r branch_name; do
  cut_branch_name=$(echo "$branch_name" | sed 's/^remotes\/origin\///')
  if ! echo "$local_branches" | grep --quiet --ignore-case "$cut_branch_name"; then
    branch_array+=("$branch_name")
  fi
done <<< "$remote_branches"

# count the number of branches found
branch_count=${#branch_array[@]}

# No branches are found, exit
if [ $branch_count -eq 0 ]; then
  echo "No branches matched: $jira_key$1"
  exit 1
elif [ $branch_count -eq 1 ]; then
  # if only one branch is found, immediately switch to it
  branch_name=${branch_array[0]}
  local_branch_name=$(echo "$branch_name" | sed 's/remotes\/origin\///')
else
  # Multiple branches are found, allow user to select which one to switch to
  # display the branch names
  echo "Select a branch:"
  for ((i = 1; i <= ${#branch_array[@]}; i++)); do
    echo "$i: ${branch_array[i - 1]}"
  done
  echo

  # prompt the user to select a branch
  read -p "Enter the number of the branch you want to checkout: " branch_num
  # Check the entered number is valid, else exit
  if [[ "$branch_num" =~ ^[0-9]+$ ]] && [ "$branch_num" -le "$branch_count" ] && [ "$branch_num" -ge "1" ]; then
    branch_name=${branch_array[$branch_num - 1]}
    # Get the local branch name (strip off `remotes/origin`)
    if [[ "$branch_name" == remotes* ]]; then
      local_branch_name=$(echo "$branch_name" | sed 's/remotes\/origin\///')
    else
      local_branch_name="$branch_name"
    fi
  else
    echo "Invalid selection"
    exit 1
  fi
fi

string=${local_branch_name//\* /}
git switch "$string"
