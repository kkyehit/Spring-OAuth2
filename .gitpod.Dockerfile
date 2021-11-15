FROM gitpod/workspace-base

# Install custom tools, runtime, etc.
RUN brew install fzf

RUN echo "gitpod/workspace-base"