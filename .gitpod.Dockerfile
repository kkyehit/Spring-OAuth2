FROM gitpod/workspace-base

RUN sudo apt-get update \
    && sudo apt-get install -y

RUN echo "gitpod/workspace-base test"
